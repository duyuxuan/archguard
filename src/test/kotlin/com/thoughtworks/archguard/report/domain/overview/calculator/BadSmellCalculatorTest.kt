package com.thoughtworks.archguard.report.domain.overview.calculator

import com.thoughtworks.archguard.report.controller.BadSmellLevel
import com.thoughtworks.archguard.report.controller.BadSmellType
import com.thoughtworks.archguard.report.controller.DashboardGroup
import com.thoughtworks.archguard.report.domain.coupling.CouplingRepository
import com.thoughtworks.archguard.report.domain.dataclumps.DataClumpsRepository
import com.thoughtworks.archguard.report.domain.deepinheritance.DeepInheritanceRepository
import com.thoughtworks.archguard.report.domain.sizing.SizingRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class BadSmellCalculatorTest {

    lateinit var classHubCalculator: ClassHubCouplingCalculator
    lateinit var dataClumpsCalculator: DataClumpsCouplingCalculator
    lateinit var deepInheritanceCalculator: DeepInheritanceCouplingCalculator
    lateinit var moduleCalculator: ModuleOverSizingCalculator
    lateinit var packageCalculator: PackageOverSizingCalculator
    lateinit var classCalculator: ClassOverSizingCalculator
    lateinit var methodCalculator: MethodOverSizingCalculator

    @MockK
    lateinit var couplingRepository: CouplingRepository

    @MockK
    lateinit var dataClumpsRepository: DataClumpsRepository

    @MockK
    lateinit var deepInheritanceRepository: DeepInheritanceRepository

    @MockK
    lateinit var sizingRepository: SizingRepository

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        classHubCalculator = ClassHubCouplingCalculator(couplingRepository)
        dataClumpsCalculator = DataClumpsCouplingCalculator(dataClumpsRepository)
        deepInheritanceCalculator = DeepInheritanceCouplingCalculator(deepInheritanceRepository)
        moduleCalculator = ModuleOverSizingCalculator(sizingRepository)
        packageCalculator = PackageOverSizingCalculator(sizingRepository)
        classCalculator = ClassOverSizingCalculator(sizingRepository)
        methodCalculator = MethodOverSizingCalculator(sizingRepository)
    }

    @Test
    fun should_calculate_class_hub_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(3, 3, 3)
        every { couplingRepository.getCouplingAboveBadSmellCalculateResult(any(), any()) } returns mockResult
        val result = classHubCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.CLASSHUB)
        assertThat(result.category).isEqualTo(DashboardGroup.COUPLING)
        assertThat(result.level).isEqualTo(BadSmellLevel.D)
        assertThat(result.count).isEqualTo(9)
    }

    @Test
    fun should_calculate_Data_clumps_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(0, 0, 0)
        every { dataClumpsRepository.getLCOM4AboveBadSmellCalculateResult(any(), any()) } returns mockResult
        val result = dataClumpsCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.DATACLUMPS)
        assertThat(result.category).isEqualTo(DashboardGroup.COUPLING)
        assertThat(result.level).isEqualTo(BadSmellLevel.A)
        assertThat(result.count).isEqualTo(0)
    }

    @Test
    fun should_calculate_deep_inheritance_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(5, 0, 0)
        every { deepInheritanceRepository.getDitAboveBadSmellCalculateResult(any(), any()) } returns mockResult
        val result = deepInheritanceCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.DEEPINHERITANCE)
        assertThat(result.category).isEqualTo(DashboardGroup.COUPLING)
        assertThat(result.level).isEqualTo(BadSmellLevel.B)
        assertThat(result.count).isEqualTo(5)
    }

    @Test
    fun should_calculate_module_sizing_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(5, 0, 0)
        val mockResultLine = BadSmellCalculateResult(1, 2, 0)
        every { sizingRepository.getModuleSizingListAbovePackageCountBadSmellResult(any(), any()) } returns mockResult
        every { sizingRepository.getModuleSizingAboveLineBadSmellResult(any(), any()) } returns mockResultLine

        val result = moduleCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.SIZINGMODULES)
        assertThat(result.category).isEqualTo(DashboardGroup.SIZING)
        assertThat(result.level).isEqualTo(BadSmellLevel.C)
        assertThat(result.count).isEqualTo(8)
    }

    @Test
    fun should_calculate_package_sizing_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(5, 0, 1)
        val mockResultLine = BadSmellCalculateResult(1, 2, 0)
        every { sizingRepository.getPackageSizingListAboveClassCountBadSmellResult(any(), any()) } returns mockResult
        every { sizingRepository.getPackageSizingAboveLineBadSmellResult(any(), any()) } returns mockResultLine

        val result = packageCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.SIZINGPACKAGE)
        assertThat(result.category).isEqualTo(DashboardGroup.SIZING)
        assertThat(result.level).isEqualTo(BadSmellLevel.D)
        assertThat(result.count).isEqualTo(9)
    }

    @Test
    fun should_calculate_class_sizing_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(0, 0, 1)
        val mockResultLine = BadSmellCalculateResult(1, 0, 0)
        every { sizingRepository.getClassSizingListAboveMethodCountBadSmellResult(any(), any()) } returns mockResult
        every { sizingRepository.getClassSizingAboveLineBadSmellResult(any(), any()) } returns mockResultLine

        val result = classCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.SIZINGCLASS)
        assertThat(result.category).isEqualTo(DashboardGroup.SIZING)
        assertThat(result.level).isEqualTo(BadSmellLevel.D)
        assertThat(result.count).isEqualTo(2)
    }


    @Test
    fun should_calculate_method_sizing_bad_smell_result() {
        val mockResult = BadSmellCalculateResult(0, 0, 0)
        every { sizingRepository.getMethodSizingAboveLineBadSmellResult(any(), any()) } returns mockResult

        val result = methodCalculator.getOverSizingOverviewItem(1)

        assertThat(result.badSmell).isEqualTo(BadSmellType.SIZINGMETHOD)
        assertThat(result.category).isEqualTo(DashboardGroup.SIZING)
        assertThat(result.level).isEqualTo(BadSmellLevel.A)
        assertThat(result.count).isEqualTo(0)
    }
}