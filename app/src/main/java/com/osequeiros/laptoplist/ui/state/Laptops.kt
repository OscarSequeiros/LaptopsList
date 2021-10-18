package com.osequeiros.laptoplist.ui.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.osequeiros.laptoplist.domain.model.Laptop

@Composable
fun Laptops(
    laptops: List<Laptop>,
    modifier: Modifier = Modifier,
    onLaptopClick: (Laptop) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        itemsIndexed(laptops) { index, laptop ->
            Laptop(laptop, laptops.size == index + 1, onClick = onLaptopClick)
        }
    }
}

@Composable
fun Laptop(
    laptop: Laptop,
    isTheFinalOne: Boolean,
    onClick: (Laptop) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onClick(laptop) }
            .padding(top = 16.dp),
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = laptop.imageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(
                    text = laptop.title,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = laptop.description,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
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
        ),
        isTheFinalOne = false
    )
}