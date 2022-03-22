package com.example.gui_basic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class utilTest {

    util util = null;

    @BeforeEach
    void setUp() { util = new util(); }

    @DisplayName("Részeredmény és végeredmény számítását végző függvény tesztje; Paraméterezett teszt csv fájlból")
    @ParameterizedTest
    @CsvFileSource(resources = "/szamitasTeszt.csv", numLinesToSkip = 1)
    void szamitasTeszt (int kezdoSsz, Double reszSzum, int tav, Double m1, Double m2, Double m3, Double tomeg, Double vartReszeredm, Double vartSzum, int vartSsz) {
        util.noOfPackages = kezdoSsz;
        util.subtotal = reszSzum;
        util.calculate(tav, m1, m2, m3, tomeg);
        assertEquals(vartReszeredm, util.partialResult);
        assertEquals(vartSzum, util.sum);
        assertEquals(vartSsz, util.noOfPackages);
    }

    @DisplayName("Térfogat méreteit sorbarendező metódus tesztje")
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3, 1, 2, 3",
            "2, 1, 3, 1, 2, 3",
            "3, 2, 1, 1, 2, 3",
            "3, 1, 2, 1, 2, 3",
            "1, 3, 2, 1, 2, 3",
            "2, 3, 1, 1, 2, 3",
            "1, 37.5, 40, 37.5, 1, 40",  //mert m1 legfeljebb 37.5 lehet, m2 pedig kevesebb
            "40, 37.5, 1, 37.5, 1, 40",
            "1, 40, 37.5, 37.5, 1, 40",
            "40, 1, 37.5, 37.5, 1, 40",
            "37.5, 1, 40, 37.5, 1, 40",
            "37.5, 40, 1, 37.5, 1, 40",
            "36, 37.6, 37.5, 37.5, 36, 37.6",  //mert 37.6 már csak m3 lehet (m1 max 37.5, m2 max 36)
            "37.5, 37.6, 36, 37.5, 36, 37.6",
            "36, 37.5, 37.6, 37.5, 36, 37.6",
            "37.5, 36, 37.6, 37.5, 36, 37.6",
            "37.6, 36, 37.5, 37.5, 36, 37.6",
            "37.6, 37.5, 36, 37.5, 36, 37.6",
    }) //i: input, o: output
    void meretSorbanTest(Double i1, Double i2, Double i3, Double o1, Double o2, Double o3) {
        util.sizeOrder(i1, i2, i3);
        assertEquals(o1, util.m1);
        assertEquals(o2, util.m2);
        assertEquals(o3, util.m3);
    }

    /*
    @Test
    @DisplayName("Legelső teszt a részeredmény és végeredmény számítását végző függvényre")
    void szamitasTeszt_v0() {
        //1. 0 <  t < 5 km, S méret (11,5x36x61 cm), 0 < m <= 1 kg, különböző darabszámok
        util.ssz = 1;
        util.subtotal = 0;
        util.szamitas(1, 61, 36, 1, 0.1);
        assertEquals(500, util.partialResult);
        assertEquals(500, util.sum);
        assertEquals(2, util.ssz);


        util.ssz = 2;
        util.subtotal = 0;
        util.szamitas(2, 61, 36, 3.2, 0.2);
        assertEquals(500, util.partialResult);
        assertEquals(425, util.sum);
        assertEquals(3, util.ssz);

        util.ssz = 4;
        util.subtotal = 0;
        util.szamitas(3, 61, 36, 8, 0.6);
        assertEquals(500, util.partialResult);
        assertEquals(425, util.sum);
        assertEquals(5, util.ssz);

        util.ssz = 5;
        util.subtotal = 0;
        util.szamitas(4, 61, 36, 10, 0.99);
        assertEquals(500, util.partialResult);
        assertEquals(375, util.sum);
        assertEquals(6, util.ssz);

        util.ssz = 6;
        util.subtotal = 0;
        util.szamitas(4, 61, 36, 11.5, 1);
        assertEquals(500, util.partialResult);
        assertEquals(375, util.sum);
        assertEquals(7, util.ssz);

        //2. 5 <= t < 100 km, M méret (19,5x36x61 cm), 1 < m <= 3 kg, különböző darabszámok
        util.ssz = 1;
        util.subtotal = 0;
        util.szamitas(5, 61, 36, 11.6, 1.1);
        assertEquals(907.5, util.partialResult);
        assertEquals(907.5, util.sum);
        assertEquals(2, util.ssz);

        util.ssz = 2;
        util.subtotal = 0;
        util.szamitas(12, 61, 36, 12, 2);
        assertEquals(907.5, util.partialResult);
        assertEquals(771.375, util.sum);
        assertEquals(3, util.ssz);

        util.ssz = 4;
        util.subtotal = 0;
        util.szamitas(54, 61, 36, 15.4, 2.32);
        assertEquals(907.5, util.partialResult);
        assertEquals(771.375, util.sum);
        assertEquals(5, util.ssz);

        util.ssz = 5;
        util.subtotal = 0;
        util.szamitas(78, 61, 36, 19, 2.9);
        assertEquals(907.5, util.partialResult);
        assertEquals(680.625, util.sum);
        assertEquals(6, util.ssz);

        util.ssz = 6;
        util.subtotal = 0;
        util.szamitas(99, 36, 61, 19.5, 3);
        assertEquals(907.5, util.partialResult);
        assertEquals(680.625, util.sum);
        assertEquals(7, util.ssz);

        //3. t >= 100 km, L méret (37,5x36x61 cm), 3 < m <= 10 kg, különböző darabszámok
        util.ssz = 1;
        util.subtotal = 0;
        util.szamitas(100, 36, 61, 19.6, 3.1);
        assertEquals(1440, util.partialResult);
        assertEquals(1440, util.sum);
        assertEquals(2, util.ssz);

        util.ssz = 2;
        util.subtotal = 0;
        util.szamitas(101, 20, 36, 61, 5);
        assertEquals(1440, util.partialResult);
        assertEquals(1224, util.sum);
        assertEquals(3, util.ssz);

        util.ssz = 4;
        util.subtotal = 0;
        util.szamitas(154, 32, 36, 61, 6.32);
        assertEquals(1440, util.partialResult);
        assertEquals(1224, util.sum);
        assertEquals(5, util.ssz);

        util.ssz = 5;
        util.subtotal = 0;
        util.szamitas(478, 37, 36, 61, 9.9);
        assertEquals(1440, util.partialResult);
        assertEquals(1080, util.sum);
        assertEquals(6, util.ssz);

        util.ssz = 6;
        util.subtotal = 0;
        util.szamitas(985, 37.5, 36, 61, 10);
        assertEquals(1440, util.partialResult);
        assertEquals(1080, util.sum);
        assertEquals(7, util.ssz);

        //4. t >= 100 km, L méret (37,5x36x61 cm), 10 < m <= 20 kg, különböző darabszámok
        util.ssz = 1;
        util.subtotal = 0;
        util.szamitas(100, 19.6, 36, 61, 10.1);
        assertEquals(1560, util.partialResult);
        assertEquals(1560, util.sum);
        assertEquals(2, util.ssz);

        util.ssz = 2;
        util.subtotal = 0;
        util.szamitas(214, 25, 36, 61, 14);
        assertEquals(1560, util.partialResult);
        assertEquals(1326, util.sum);
        assertEquals(3, util.ssz);

        util.ssz = 4;
        util.subtotal = 0;
        util.szamitas(327, 34.6, 36, 61, 17.13);
        assertEquals(1560, util.partialResult);
        assertEquals(1326, util.sum);
        assertEquals(5, util.ssz);

        util.ssz = 5;
        util.subtotal = 0;
        util.szamitas(543, 36.12, 36, 61, 19.9);
        assertEquals(1560, util.partialResult);
        assertEquals(1170, util.sum);
        assertEquals(6, util.ssz);

        util.ssz = 6;
        util.subtotal = 0;
        util.szamitas(612, 37.5, 36, 61, 20);
        assertEquals(1560, util.partialResult);
        assertEquals(1170, util.sum);
        assertEquals(7, util.ssz);

    } */


}