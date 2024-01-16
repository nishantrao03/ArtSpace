package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    val images = Array(7) {
        when (it) {
            0 -> painterResource(id = R.drawable.image_1)
            1 -> painterResource(id = R.drawable.image_2)
            2 -> painterResource(id = R.drawable.image_3)
            3 -> painterResource(id = R.drawable.image_4)
            4 -> painterResource(id = R.drawable.image_5)
            5 -> painterResource(id = R.drawable.image_6)
            6 -> painterResource(id = R.drawable.image_7)
            else -> throw IndexOutOfBoundsException("Index out of bounds: $it")
        }
    }
    val desc = intArrayOf(
        R.string.nuclear_bomb_blast,
        R.string.scenic_beauty,
        R.string.peacock_feather,
        R.string.great_wall_of_china,
        R.string.photographer,
        R.string.sad_man,
        R.string.tree_beside_river
    )
    val names = intArrayOf(
        R.string.amit_gupta,
        R.string.bobby_singh,
        R.string.catherine,
        R.string.dolly,
        R.string.falendra_kumar,
        R.string.gagan,
        R.string.pulkit_sharma
    )
    val year = intArrayOf(R.string._2015,
        R.string._2018,
        R.string._2022,
        R.string._2011,
        R.string._2021,
        R.string._2016,
        R.string._2023
    )
    var imgIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier

            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        ){
            Image(
                painter = images[imgIndex],
                contentDescription = stringResource(id = desc[imgIndex]),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        ImageDetails(desc = desc[imgIndex], name = names[imgIndex], year = year[imgIndex])
        Spacer(modifier = Modifier.height(50.dp))
        Row(

        ) {
            Box(
                modifier = Modifier
                    .weight(1f)

            ) {
                ScrollButton(
                    text = stringResource(id = R.string.previous),
                    onClicked = {
                        if (imgIndex != 0) {
                            imgIndex--
                        } else {
                            imgIndex = 6
                        }
                    },
                    modifier = Modifier

                        .width(50.dp)
                         // Adjust padding as needed
                )
            }

            Box {
                ScrollButton(
                    text = stringResource(id = R.string.next),
                    onClicked = {
                        if (imgIndex != 6) {
                            imgIndex++
                        } else {
                            imgIndex = 0
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
                        .align(Alignment.CenterEnd)
                         // Adjust padding as needed
                )
            }
        }
    }
}

@Composable
fun ScrollButton(
    text: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(onClick = onClicked ) {
        Text(text)
    }
}

@Composable
fun ImageDetails(
    @StringRes desc: Int,
    @StringRes name: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    val curryear = stringResource(year)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(213, 213, 213))
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(desc),
            fontSize = 28.sp, // Set your desired font size here
            //modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(

        ) {
            Text(text = stringResource(name))
            Text(text = " ($curryear)")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}