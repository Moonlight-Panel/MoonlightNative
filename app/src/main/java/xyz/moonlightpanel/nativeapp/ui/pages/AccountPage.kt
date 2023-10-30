package xyz.moonlightpanel.nativeapp.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.api.ApiClient
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.Header

@Composable
fun AccountPage(){
    val apiClient = ApiClient.INSTANCE;
    val accountManger = apiClient.accountManagementApi;

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Header(text = "Account")
        }
    }
}

fun AccountPagePreloader(onFinish: Delegate){
    val apiClient = ApiClient.INSTANCE;
    val accountManger = apiClient.accountManagementApi;

    accountManger.loadData({
        LayoutManager.showLoadingIndicator()
    }, {
        LayoutManager.hideLoadingIndicator()
        onFinish.invoke()
    })
}

@Preview
@Composable
fun AccountPagePreview(){
    AccountPage()
}