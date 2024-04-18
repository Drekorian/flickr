package cz.drekorian.android.flickr.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.drekorian.android.flickr.ui.controls.latest.LatestScreen
import cz.drekorian.android.flickr.ui.controls.search.SearchScreen

@Composable
internal fun NavGraph(
    startDestination: String = Destinations.Latest,
) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        route(Destinations.Latest) { entry -> LatestScreen(actions, entry) }
        route(Destinations.Search) { entry -> SearchScreen(actions, entry) }
    }
}

private fun NavGraphBuilder.route(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(route = route, content = content)
}

class Actions(
    private val navController: NavHostController,
) {
    fun showLatest() {
        navController.navigate(Destinations.Latest)
    }

    fun showSearch() {
        navController.navigate(Destinations.Search)
    }
}

internal object Destinations {
    const val Latest = "latest"
    const val Search = "search"
}
