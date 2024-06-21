package com.ani.android.contentprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ani.android.contentprovider.ui.theme.ContentProviderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val repository = ContactRepository(this)

        val vm = ContactViewModel(repository)

        setContent {
            ContentProviderTheme {
                vm.loadContactsNormal()
               ContactList(vm = vm)
            }
        }
    }
}

@Composable
fun ContactList(vm: ContactViewModel) {

    val state: List<Contact>? by vm.listFlow.collectAsState()

    state?.let {
        LazyColumn {
            items(it) { cnt ->
                ContactItem(name = cnt.name, number = cnt.number)
            }
        }
    }
}

@Composable
fun ContactItem(name: String, number: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Text(text = number)
    }
}

data class Contact(
    val name: String,
    val number: String
)




