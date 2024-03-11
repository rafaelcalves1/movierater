package com.projects.movierater.features.home.components.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.projects.movierater.R
import com.projects.movierater.ui.extensions.shimmerEffect
import com.projects.movierater.util.CommonUrl

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    imgUrl: String,
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_m))),
        model = ImageRequest.Builder(LocalContext.current)
            .data(CommonUrl.IMAGE_PATH + imgUrl)
            .crossfade(true)
            .build(),
        loading = {
            MovieItemShimmer()
        },
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun MovieItemShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(140.dp)
            .height(280.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .shimmerEffect()
    )
}