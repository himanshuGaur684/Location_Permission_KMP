package gaur.himanshu.locationpermission.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import gaur.himanshu.locationpermission.permission.AndroidLocationPermission

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationPermission()
                }
            }
        }
    }
}

@Composable
fun LocationPermission(modifier: Modifier = Modifier) {

    val status = remember { mutableStateOf("") }


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it) {
                status.value = "Permission granted"
            } else {
                status.value = "denied"
            }
        }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = status.value)

        Spacer(modifier = Modifier.height(12.dp))

        val context = LocalContext.current
        Button(onClick = {
            AndroidLocationPermission(context, launcher).requestLocationPermission {

            }
        }) {
            Text(text = "Give Permission")
        }
    }
}

