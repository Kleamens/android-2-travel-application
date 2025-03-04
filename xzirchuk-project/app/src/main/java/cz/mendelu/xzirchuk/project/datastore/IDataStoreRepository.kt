import kotlinx.coroutines.flow.Flow

interface IDataStoreRepository {
    suspend fun setIsDark(isDark:Boolean)

     fun getIsDark(): Flow<Boolean?>
}