package com.example.stablestable.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.BedroomBaby
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BedroomBaby
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.data.classes.NavigationBarItem
import com.example.stablestable.ui.home.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScaffold(
    content: @Composable (PaddingValues) -> Unit,
    screen: String,
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    onLogout: () -> Unit,
    currentScreen: String
) {
    val viewModel = viewModel<HomeViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = listOf(
        NavigationBarItem(
            title = stringResource(R.string.riders),
            route = "stableUsers_screen",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
        ),
        NavigationBarItem(
            title = stringResource(R.string.horses),
            route = "stableHorses_screen",
            selectedIcon = Icons.Filled.BedroomBaby,
            unselectedIcon = Icons.Outlined.BedroomBaby,
            hasNews = false,
        ),
        NavigationBarItem(
            title = stringResource(R.string.home),
            route = "home_screen",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        NavigationBarItem(
            title = stringResource(R.string.shifts),
            route = "shifts_screen",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false,
        ),
        NavigationBarItem(
            title = stringResource(R.string.board),
            route = "board",
            selectedIcon = Icons.AutoMirrored.Filled.Message,
            unselectedIcon = Icons.AutoMirrored.Outlined.Message,
            hasNews = false,
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Drawer Content
            BurgerMenu { destination ->
                when (destination) {
                    "Profile" -> goToMyProfile()
                    "Logout" -> onLogout()
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                screen,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        actions = {
                            IconButton(onClick = { viewModel.setNotificationDialogStateToTrue() }) {
                                Icon(
                                    imageVector = Icons.Filled.Notifications,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    NavigationBar {
                        items.forEach { item ->
                            NavigationBarItem(
                                selected = item.route == currentScreen,
                                onClick = {
                                    when (item.route) {
                                        "stableUsers_screen" -> goToRiders()
                                        "home_screen" -> goToHome()
                                        "shifts_screen" -> goToShifts()
                                        "stableHorses_screen" -> goToHorses()
                                    }
                                },
                                label = {
                                    Text(text = item.title)
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if (item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (item.route == currentScreen) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                })
                        }
                    }
                }
            ) { contentPadding ->
                content(contentPadding)
            }
        }
    )

    if (viewModel.showNotificationDialog) {
        NotificationsDialog()
    }
}
