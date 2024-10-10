import SwiftUI
import shared

struct ContentView: View {

    let locationPermission = IosLocationPermission()
    
    @State private var isGranted : Bool = false
    
    
    var body: some View {
        VStack{
            Text(isGranted ? "Permission Granted ":"Denied")
            Button(action: {
                requestLocationPermission()
            }, label: {
                Text("Give Permission")
            })
        }.onAppear{
            isGranted = locationPermission.isPermissionGranted()
        }

    }
    
    func requestLocationPermission(){
        locationPermission.requestLocationPermission{isGranted in
            DispatchQueue.main.async{
                self.isGranted = isGranted.boolValue
            }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
