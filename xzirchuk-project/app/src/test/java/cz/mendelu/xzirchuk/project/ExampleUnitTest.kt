package cz.mendelu.xzirchuk.project

import cz.mendelu.xzirchuk.project.database.Location
import cz.mendelu.xzirchuk.project.database.LocationsDAO_Impl
import cz.mendelu.xzirchuk.project.database.LocationsDatabase_Impl
import cz.mendelu.xzirchuk.project.database.LocationsRepoImpl
import cz.mendelu.xzirchuk.project.ui.screens.textRecognition.TextRecognitionViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    suspend fun TestUpdateLocation(){
        var db = LocationsDatabase_Impl()
        var dao = LocationsDAO_Impl(db)
        var locationsRepo :LocationsRepoImpl = LocationsRepoImpl(dao)
            locationsRepo.insertLocation(Location(
                name = "test",
                description = "test",
                geocoded_name = "test",
                latitude = 0.0,
                longitude = 0.0
            ))


            locationsRepo.getAll().collect{
                assert(it.first().name == "test").also {
                    locationsRepo.delete(it as Location)
                }
            }
    }

    @Test
    fun Testtranslate(){
        var viewModel = TextRecognitionViewModel()

        viewModel.translateText("Hello"){

        }
        assert(viewModel.uiState.value.data == "Ahoj")
    }
}