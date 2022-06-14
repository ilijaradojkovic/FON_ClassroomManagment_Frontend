package com.example.fon_classroommanagment_frontend.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.ClassroomCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun All_Classrooms(
    navHostController: NavHostController,
    allClassroomsViewModel: AllClassroomsViewModel,

    ) {
    val classrooms = allClassroomsViewModel.classrooms
    val seachedClassrooms=allClassroomsViewModel.searchedClassrooms

    val layoutState =     rememberLazyListState()

    val scrollContext = rememberScrollContext(layoutState)

    val networkState by allClassroomsViewModel.networkState



    LaunchedEffect(key1 = scrollContext.isBottom){
        allClassroomsViewModel.getAllClassrooms()
    }


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(state = layoutState,modifier=Modifier, contentPadding = PaddingValues(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){
            itemsIndexed(
                if(allClassroomsViewModel.shouldDisplaySeachData())
                    seachedClassrooms else classrooms,

            ){index,item->


                    Row(
                        Modifier
                    ) {
                        ClassroomCard(
                            item.name,
                            item.isRC,
                            item.projector,
                            item.number_of_seats
                        ) { navHostController.navigate(Screen.DetailsClassroomScreen.route) }

                    }




            }

            item{
                AnimatedVisibility(scrollContext.isBottom  ) {
                    if(networkState.isError)
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp), horizontalArrangement = Arrangement.Center){
                           // Icon(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "", modifier = Modifier.size(24.dp))
                           LottieAnimation(lottieAnim = R.raw.no_wifi, iterations = LottieConstants.IterateForever)

                        }
                    else if(networkState.isLoading){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp), horizontalArrangement = Arrangement.Center){
                            // Icon(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "", modifier = Modifier.size(24.dp))
                            LottieAnimation(lottieAnim = R.raw.no_wifi, iterations = LottieConstants.IterateForever)

                        }
                    }
                }
            }

        }





    }

}
val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

val LazyListState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0

data class ScrollContext(
    val isTop: Boolean,
    val isBottom: Boolean,
)

@Composable
fun rememberScrollContext(listState: LazyListState): ScrollContext {
    val scrollContext = remember {
        derivedStateOf {
            ScrollContext(
                isTop = listState.isFirstItemVisible,
                isBottom = listState.isLastItemVisible
            )
        }
    }
    return scrollContext.value
}
