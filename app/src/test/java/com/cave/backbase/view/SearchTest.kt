package com.cave.backbase.view

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.cave.backbase.data.local.CitiesLocal
import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.CityLocation
import com.cave.backbase.data.model.Result
import com.cave.backbase.data.repositories.CitiesRepository
import com.cave.backbase.di.component.applicationComponent
import com.cave.backbase.ui.list.ListViewModel
import com.cave.backbase.utils.extentions.prefixSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class KoinModuleTest : KoinTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var repository: CitiesRepository

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: ListViewModel
    private lateinit var list: List<City>
    private lateinit var citiesLocal: CitiesLocal

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        viewModel = ListViewModel(savedStateHandle, repository)
        citiesLocal = mock(CitiesLocal::class.java)
        list = listOf<City>(
            City(
                country = "Iran",
                city = "Tehran",
                _id = 123,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Esfahan",
                _id = 3,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Shiraz",
                _id = 13,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Tabriz",
                _id = 53,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Mashhad",
                _id = 12143,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Gorgan",
                _id = 1233,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Bandar Abas",
                _id = 1123,
                CityLocation(lat = 23.321, lon = 3.312)
            )
        )

        stopKoin() // to remove 'A Koin Application has already been started'
        startKoin {
            androidContext(mock(Application::class.java))
            modules(applicationComponent)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSuccessForCitiesLocal() = runTest {
        val result = citiesLocal.getCitiesList()
        Mockito.mockStatic(CitiesLocal::class.java).use { mocked ->
            mocked.`when`<Result.Success<List<City>>> { result }
                .thenReturn(Result.Success(list))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testErrorForCitiesLocal() = runTest {
        val result = citiesLocal.getCitiesList()
        Mockito.mockStatic(CitiesLocal::class.java).use { mocked ->
            mocked.`when`<Result.Error> { result }
                .thenReturn(Result.Error("ReaderError"))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoadingForCitiesLocal() = runTest {
        val result = citiesLocal.getCitiesList()
        Mockito.mockStatic(CitiesLocal::class.java).use { mocked ->
            mocked.`when`<Result.Loading> { result }
                .thenReturn(Result.Loading)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `return expected list for 'Tehran' city, it should include 1 item`() = runTest {
        val expectedList = listOf<City>(
            City(
                country = "Iran",
                city = "Tehran",
                _id = 123,
                CityLocation(lat = 23.321, lon = 3.312)
            )
        )
        val cityNameForSearch = "Tehran"
        val sortedList = list.sortedWith(compareBy({ it.city }, { it.country }))
        val result = sortedList.prefixSearch<City>(0, list.size, prefix = cityNameForSearch)
        assertEquals(result, expectedList)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `return expected list for 'London' city, it should include null`() = runTest {
        val expectedList = null
        val cityNameForSearch = "London"
        val sortedList = list.sortedWith(compareBy({ it.city }, { it.country }))
        val result = sortedList.prefixSearch<City>(0, list.size, prefix = cityNameForSearch)
        assertEquals(result, expectedList)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `return expected list for prefix 'T' city, it should include 2 items`() = runTest {
        val expectedList = listOf<City>(
            City(
                country = "Iran",
                city = "Tabriz",
                _id = 53,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Tehran",
                _id = 123,
                CityLocation(lat = 23.321, lon = 3.312)
            )
        )

        val cityNameForSearch = "T"
        val sortedList = list.sortedWith(compareBy({ it.city }, { it.country }))
        val result = sortedList.prefixSearch<City>(0, list.size, prefix = cityNameForSearch)
        assertEquals(result, expectedList)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `return expected list for prefix 't'(case InSensitive) city, it should include 2 items`() = runTest {
        val expectedList = listOf<City>(
            City(
                country = "Iran",
                city = "Tabriz",
                _id = 53,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Tehran",
                _id = 123,
                CityLocation(lat = 23.321, lon = 3.312)
            )
        )

        val cityNameForSearch = "T"
        val sortedList = list.sortedWith(compareBy({ it.city }, { it.country }))
        val result = sortedList.prefixSearch<City>(0, list.size, prefix = cityNameForSearch)
        assertEquals(result, expectedList)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `return expected list for prefix 'T' city, it should not equals due to sorting`() = runTest {
        val expectedList = listOf<City>(
            City(
                country = "Iran",
                city = "Tehran",
                _id = 123,
                CityLocation(lat = 23.321, lon = 3.312)
            ),
            City(
                country = "Iran",
                city = "Tabriz",
                _id = 53,
                CityLocation(lat = 23.321, lon = 3.312)
            )
        )

        val cityNameForSearch = "T"
        val sortedList = list.sortedWith(compareBy({ it.city }, { it.country }))
        val result = sortedList.prefixSearch<City>(0, list.size, prefix = cityNameForSearch)
        assertNotEquals(result, expectedList)
    }

    @After
    fun after() {
        stopKoin()
        mainThreadSurrogate.close()
    }
}
