package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class RomanNumeralTest {

    @Test
    fun romanStringCanBeConvertedToInt() {
        val expectedIntValueByRomanNumeral = mapOf(
            Pair("I", 1),
            Pair("XLII", 42),
            Pair("XCIX", 99),
            Pair("C", 100),
            Pair("CXI", 111),
            Pair("CMXCIX", 999),
            Pair("MMXXIII", 2023),
            Pair("MMMCMXCIX", 3999),
        )

        expectedIntValueByRomanNumeral.forEach { (roman, expectedValue) ->
            val actualValue = RomanNumeral(roman).toInt()
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    fun parsingInvalidStringThrows() {
        assertThatExceptionOfType(NumberFormatException::class.java)
            .isThrownBy { RomanNumeral("XXXX") }

        assertThatExceptionOfType(NumberFormatException::class.java)
            .isThrownBy { RomanNumeral("") }
    }

    @Test
    fun intCanBeConvertedToRomanString() {
        val expectedRomanNumeralByIntValue = mapOf(
            Pair(1, "I"),
            Pair(42, "XLII"),
            Pair(99, "XCIX"),
            Pair(100, "C"),
            Pair(111, "CXI"),
            Pair(999, "CMXCIX"),
            Pair(2023, "MMXXIII"),
            Pair(3999, "MMMCMXCIX"),
        )

        expectedRomanNumeralByIntValue.forEach { (intValue, expectedRoman) ->
            val actualRoman = RomanNumeral(intValue).toString()
            assertThat(actualRoman).isEqualTo(expectedRoman);
        }
    }

    @Test
    fun valueMustBeInRange() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { RomanNumeral(-100) }

        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { RomanNumeral(0) }

        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { RomanNumeral(4000) }

        Assertions.assertThatNoException().isThrownBy { RomanNumeral(1) }
        Assertions.assertThatNoException().isThrownBy { RomanNumeral(3999) }
    }

    @Test
    fun addingTwoNumeralsYieldsTheirSum() {
        val roman2 = RomanNumeral(2)
        val roman3 = RomanNumeral("III")
        val sum = roman2 + roman3
        assertThat(sum.toString()).isEqualTo("V")
        assertThat(sum.toInt()).isEqualTo(5)
    }

    @Test
    fun equalsAndHashCode() {
        val roman1a = RomanNumeral(1)
        val roman1b = RomanNumeral("I")
        val roman2 = RomanNumeral(2)

        // Note:
        // Two equal objects must have the same hash code, but two unequal objects
        // are *NOT* required to have different hash codes!
        assertThat(roman1a.hashCode())
            .isEqualTo(roman1b.hashCode())
        Assertions.assertThatObject(roman1a)
            .isEqualTo(roman1a)
            .isEqualTo(roman1b)
            .isNotEqualTo(roman2)
    }
}
