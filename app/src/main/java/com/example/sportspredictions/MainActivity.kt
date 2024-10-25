package com.example.sportspredictions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sportspredictions.ui.theme.SportsPredictionsTheme
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsPredictionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SportsPredictionsTheme {
        Greeting("Android")
    }
}

fun skrapeESPN() {
    val html: String = skrape(HttpFetcher) {
        // perform a GET request to the specified URL
        request {
            url = "https://www.espn.com/nfl/team/stats/_/name/jax/jacksonville-jaguars"
        }

        response {
            // retrieve the HTML element from the
            // document as a string
            htmlDocument {
                //parsed Doc object is available here
                html
            }
        }
    }
    // print the source HTML of the target page
    println(html)
}