package cz.mendelu.xzirchuk.project.ui.screens.desinationsList

import android.location.Location

sealed class DestinationsListUIState {
    object Default:DestinationsListUIState()

    object Loading:DestinationsListUIState()

    object SelectedDeckChanged:DestinationsListUIState()
    class Success(val decks: List<Location>) : DestinationsListUIState()
}