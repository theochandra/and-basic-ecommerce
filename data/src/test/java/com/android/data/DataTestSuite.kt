package com.android.data

import com.android.data.mapper.DataMapperTest
import com.android.data.repository.BasicEcommerceRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DataMapperTest::class,
    BasicEcommerceRepositoryImplTest::class
)
class DataTestSuite