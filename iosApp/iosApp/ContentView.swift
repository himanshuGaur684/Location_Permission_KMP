import SwiftUI
import shared

struct ContentView: View {
    
    let locationPermission = IosLocationPermission()
    
    @State private var isPermissionGranted : Bool = false
    
    var body: some View {
        VStack{
            Text(isPermissionGranted ? "Permission Granted" : "Permission Denied").padding()
            
            Button(action:{
                requestLocationPermission()
            },label: {Text("Request Location Permission")})
            
        }.onAppear(perform: {
            isPermissionGranted = locationPermission.isPermissionGranted()
        })
    }
    
    func requestLocationPermission(){
        locationPermission.requestLocationPermission{  isGranted in
            DispatchQueue.main.async{
                isPermissionGranted = isGranted.boolValue
            }}
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
