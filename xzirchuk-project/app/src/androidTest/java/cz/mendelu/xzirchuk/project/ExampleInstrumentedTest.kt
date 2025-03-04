package cz.mendelu.xzirchuk.project

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.xzirchuk.project.ui.screens.TestingConstants
import cz.mendelu.xzirchuk.project.ui.theme.FontDark
import cz.mendelu.xzirchuk.project.ui.theme.FontLight
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
//    @get:Rule(order = 0)
//    var hiltRule = HiltAndroidRule(this)

    lateinit var mockNavigator: DestinationsNavigator

//    @Inject
//    lateinit var apirepo: ReverseGeocodeRepoImpl
//    @Inject
//    lateinit var weatherrepo: APIWeatherImpl
//    @Inject
//    lateinit var repo: LocationsRepoImpl
//
//    @Before
//    fun init() {
//
//
//        hiltRule.inject()
//    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSearchBar_Exists(){

        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.SEARCH_FIELD),5000L)
    }
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSearchBar_HasCorrectText(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.SEARCH_FIELD),5000L)
        var text = "TestText"
        composeTestRule.onNodeWithTag(TestingConstants.SEARCH_FIELD).performTextInput(text)
        composeTestRule.onNodeWithTag(TestingConstants.SEARCH_FIELD).assert(hasText(text))

    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNavigateToTranslateScreen(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.TRANSLATE_NAV_BUTTON),6000L)
        composeTestRule.onNodeWithTag(TestingConstants.TRANSLATE_NAV_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TestingConstants.TRANSLATE_SCREEN_TEXT).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNavigateToMapScreen(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.MAP_NAV_BUTTON),6000L)
        composeTestRule.onNodeWithTag(TestingConstants.MAP_NAV_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TestingConstants.MAP_SCREEN_MAP).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNavigateToStatScreen(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.STAT_NAV_BUTTON),6000L)
        composeTestRule.onNodeWithTag(TestingConstants.STAT_NAV_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TestingConstants.STAT_SCREEN_TEXT).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNavigateToSettings(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.MAIN_SCREEN_BUTTON),6000L)
        composeTestRule.onNodeWithTag(TestingConstants.MAIN_SCREEN_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_SCREEN_TEXT).assertExists()
        composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_SCREEN_TEXT).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testChangeTheme(){
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(TestingConstants.MAIN_SCREEN_BUTTON),6000L)
        composeTestRule.onNodeWithTag(TestingConstants.MAIN_SCREEN_BUTTON).performClick()
        if(isDarkTheme.isDarkTheme){
            composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_LIGHT_THEME_BUTTON).performClick()
            composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_SCREEN_TEXT).assertBackgroundColor(
                FontLight)
        }else{
            composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_DARK_THEME_BUTTON).performClick()
            composeTestRule.onNodeWithTag(TestingConstants.SETTINGS_SCREEN_TEXT).assertBackgroundColor(FontDark)

        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("cz.mendelu.xzirchuk.project", appContext.packageName)
    }
}
fun SemanticsNodeInteraction.assertBackgroundColor(expectedBackground: Color) {
    val capturedName = captureToImage().colorSpace.name
    assertEquals(expectedBackground.colorSpace.name, capturedName)
}