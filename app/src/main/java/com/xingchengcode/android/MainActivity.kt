package com.xingchengcode.android

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xingchengcode.android.ui.theme.XingchengcodeTheme
import com.xingchengcode.android.ui.widget.TextFieldWithClear
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XingchengcodeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetSystemUi(color = Color(0xFFF6F6F6))
                    Scaffold(
                        topBar = {
                            TopAppBar()
                        }
                    ) {
                        Content()
                    }
                }
            }
        }
    }

    @Composable
    fun InputDialog(
        title: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        expand: Boolean,
        onDismissRequest: (String) -> Unit
    ) {
        if (expand) {
            var value by remember {
                mutableStateOf("")
            }
            AlertDialog(
                onDismissRequest = { onDismissRequest("") },
                confirmButton = {
                    TextButton(onClick = { onDismissRequest(value) }) {
                        Text(text = "确定")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onDismissRequest("") }) {
                        Text(text = "取消")
                    }
                },
                title = title,
                text = {
                    TextFieldWithClear(
                        value = value,
                        onValueChange = { value = it },
                        Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { onDismissRequest(value) }),
                        placeholder = placeholder
                    )
                }
            )
        }
    }

    @Composable
    private fun Content() {
        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .background(Color(43, 166, 103))
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //                                    Text(
                    //                                        text = "通信大数据行程卡",
                    //                                        fontSize = 30.sp,
                    //                                        color = Color.White,
                    //                                        fontWeight = FontWeight.Black,
                    //                                        modifier = Modifier.padding(top = 32.dp)
                    //                                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(id = R.drawable.title),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = "疫情防控，人人有责",
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                        fontWeight = FontWeight.Thin
                    )
                    val context = LocalContext.current
                    Card()
                    Text(
                        text = "结果包含您在前14天内到访的国家（地区）与停留4小时以上的国内城市",
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "色卡仅对到访地作提醒，不关联健康状况",
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Row(
                        Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(1.dp)
                                .weight(1f)
                                .background(Color.White)
                        )
                        Text(
                            text = "本服务联合提供",
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Box(
                            Modifier
                                .height(1.dp)
                                .weight(1f)
                                .background(Color.White)
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.caict),
                            contentDescription = "中国通信院"
                        )
                        Image(
                            painter = painterResource(id = R.drawable.china_telecom),
                            contentDescription = "中国电信"
                        )
                        Image(
                            painter = painterResource(id = R.drawable.china_mobile),
                            contentDescription = "中国移动"
                        )
                        Image(
                            painter = painterResource(id = R.drawable.china_unicom),
                            contentDescription = "中国联通"
                        )
                    }
                    Text(
                        text = "客服热线：10000 / 10086 / 10010",
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
            Box(Modifier.background(Color(248, 248, 248))) {
                BottomContent()
            }
        }
    }

    @Composable
    private fun Card() {
        Surface(shape = RoundedCornerShape(12.dp), color = Color.White) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val phone by AppGlobalData.getPhone().collectAsState(initial = "请选择")
                    var expand by remember {
                        mutableStateOf(false)
                    }
                    Text(
                        text = if (phone.isNotEmpty()) "${phone.formatPhone()}的动态行程卡" else "点击此处输入手机号",
                        color = Color(0xFF47464C),
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.clickable {
                            expand = true
                        }
                    )
                    InputDialog(
                        title = { Text(text = "请输入手机号") },
                        placeholder = { Text(text = "手机号") },
                        expand = expand,
                        onDismissRequest = {
                            expand = false
                            if (it.isNotEmpty()) {
                                AppGlobalData.setPhone(it)
                            }
                        }
                    )
                    val time by remember {
                        mutableStateOf(
                            SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(
                                System.currentTimeMillis()
                            )
                        )
                    }
                    Text(
                        text = "更新于：${time}",
                        color = Color(0xFF92949E),
                        modifier = Modifier.padding(vertical = 12.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    var width by remember {
                        mutableStateOf(150)
                    }
                    var height by remember {
                        mutableStateOf(150)
                    }
                    var isInflation by remember {
                        mutableStateOf(true)
                    }
                    val scopeState = rememberCoroutineScope()

                    remember {
                        scopeState.launch {
                            while (true) {
                                delay(500 / 30)
                                if (isInflation) {
                                    width++
                                    height++
                                    if (width > 180 && height > 180) {
                                        isInflation = false
                                    }
                                } else {
                                    width--
                                    height--
                                    if (width < 150 && height < 150) {
                                        isInflation = true
                                    }
                                }

                            }
                        }
                    }
                    Box(Modifier.size(180.dp), Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.xingcheng),
                            contentDescription = null,
                            Modifier.size(width.dp, height.dp)
                        )
                    }
                    Divider(
                        color = Color(0xFFE4E4E4),
                        modifier = Modifier.padding(
                            bottom = 12.dp,
                            top = 24.dp
                        )
                    )
                    val place by AppGlobalData.getPlace().collectAsState(initial = "请选择")
                    var expand2 by remember {
                        mutableStateOf(false)
                    }
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(
                                        0xFF92949E
                                    )
                                )
                            ) {// SpanStyle类似于Span标签，是行内元素
                                append("您于前14天内到达或途经：")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(
                                        0xFF47464C
                                    ),
                                    fontWeight = FontWeight.ExtraBold
                                )
                            ) {
                                append(if (place.isNotEmpty()) place else "点击此处输入地点")
                            }
                        },
                        fontSize = 16.sp,
                        modifier = Modifier.clickable {
                            expand2 = true
                        }
                    )
                    InputDialog(
                        title = { Text(text = "请输入地点") },
                        placeholder = { Text(text = "地点") },
                        expand = expand2,
                        onDismissRequest = {
                            expand2 = false
                            if (it.isNotEmpty()) {
                                AppGlobalData.setPlace(it)
                            }
                        })
                }
            }
        }
    }

    @Composable
    private fun BottomContent() {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.padding(horizontal = 32.dp, vertical = 8.dp)) {
                Text(
                    text = "一证通查来了!", color = Color(0xFF47464B),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "立即点击进入", color = Color(0xFF47464B),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        end = 32.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .wrapContentSize()
            ) {
                Column(
                    Modifier.padding(vertical = 5.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "全国移动电话卡\"一证通查\"",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "防范诈骗，保护你我", color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }

    fun String.formatPhone(): String {
        return this.replace(Regex("""(\d{3})\d{4}(\d{4})"""), "$1****$2");
    }

    @Composable
    private fun TopAppBar() {
        val content = LocalContext.current
        TopAppBar(backgroundColor = Color(0xFFF6F6F6)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { if (content is Activity) content.finish() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "返回"
                    )
                }
                Text(text = "通信行程卡")
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(0.5.dp, Color(229, 229, 229)),
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth()
                        .height(32.dp),
                    color = Color(251, 251, 251)
                ) {
                    Row(
                        Modifier
                            .wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            Modifier
                                .fillMaxHeight()
                                .clickable {

                                }, Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.wechat_more),
                                contentDescription = "更多",
                                Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(20.dp)
                            )
                        }

                        Box() {
                            Box(
                                Modifier
                                    .padding(vertical = 2.dp)
                                    .height(20.dp)
                                    .width(0.5.dp)
                                    .background(Color(229, 229, 229))
                            )
                        }

                        Box(
                            Modifier
                                .fillMaxHeight()
                                .clickable {
                                    if (content is Activity) content.finish()
                                }, Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.wechat_close),
                                contentDescription = "关闭",
                                Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    XingchengcodeTheme {
        Greeting("Android")
    }
}

@Composable
fun SetSystemUi(color: Color) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
//                    将所有系统栏颜色更新为透明，如果我们在浅色主题中使用深色图标
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = useDarkIcons
        )
        // setStatusBarsColor() and setNavigationBarsColor() also exist
    }
}