package com.example.fon_classroommanagment_frontend.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.ClassroomCard

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

    val isLoading_isError_DP= animateDpAsState(targetValue = if(networkState.isError || networkState.isLoading) 60.dp else 0.dp)


    LaunchedEffect(key1 = scrollContext.isBottom){
        if(scrollContext.isBottom)
            if(allClassroomsViewModel.searchText.value.isEmpty())
                allClassroomsViewModel.getAllClassrooms()
            else allClassroomsViewModel.getMoreSearchData()
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
                        ) { navHostController.navigate(Screen.DetailsClassroomScreen.route+"?classroomId="+item.id) }
                    }
            }
            item{
                AnimatedVisibility(scrollContext.isBottom ) {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(isLoading_isError_DP.value), horizontalArrangement = Arrangement.Center) {

                    if(networkState.isError)
                           LottieAnimation(lottieAnim = R.raw.no_wifi, iterations = LottieConstants.IterateForever)


                    else if(networkState.isLoading){

                            LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations = LottieConstants.IterateForever)


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
