package com.osequeiros.laptoplist.ui.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.osequeiros.laptoplist.domain.model.Laptop

@Composable
fun Laptops(laptops: List<Laptop>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp),
    ) {
        itemsIndexed(laptops) { index, laptop ->
            Laptop(laptop, laptops.size == index + 1)
        }
    }
}

@Composable
fun Laptop(laptop: Laptop, isTheFinalOne: Boolean) {
    Column(
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Image(
            painter = rememberImagePainter(laptop.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = laptop.title,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = laptop.description,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        if (!isTheFinalOne) {
            Divider(
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview
@Composable
fun LaptopPreview() {
    Laptop(
        laptop =
        Laptop(
            1,
            "MacBook Pro",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis dapibus va",
            imageUrl = "https://picsum.photos/100/100?image=0"
        ), isTheFinalOne = false
    )
}