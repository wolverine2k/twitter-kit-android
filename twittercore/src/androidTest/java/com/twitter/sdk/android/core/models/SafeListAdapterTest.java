package com.twitter.sdk.android.core.models;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SafeListAdapterTest extends TwitterAndroidTestCase {

    private static final String TEST_JSON_LIST_NULL = "{\"list\":null}";
    private static final String TEST_JSON_LIST_EMPTY = "{\"list\":[]}";
    private static final String TEST_JSON_LIST_VALUES = "{\"list\":[32,36]}";

    private static final int TEST_ANY_NUMBER = 100;

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new GsonBuilder().registerTypeAdapterFactory(new SafeListAdapter()).create();
    }

    public void testDeserialization_nullListModel1() {
        final Model1 model = gson.fromJson(TEST_JSON_LIST_NULL, Model1.class);
        assertEquals(Collections.EMPTY_LIST, model.listOfIntegers);
    }

    public void testDeserialization_emptyListModel1() {
        final Model1 model = gson.fromJson(TEST_JSON_LIST_EMPTY, Model1.class);
        assertEquals(Collections.EMPTY_LIST, model.listOfIntegers);
    }

    public void testDeserialization_validListModel1() {
        final Model1 model = gson.fromJson(TEST_JSON_LIST_VALUES, Model1.class);
        try {
            model.listOfIntegers.add(Integer.valueOf(TEST_ANY_NUMBER));
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    public void testDeserialization_nullListModel2() {
        final Model2 model = gson.fromJson(TEST_JSON_LIST_NULL, Model2.class);
        assertEquals(Collections.EMPTY_LIST, model.listOfLongs);
    }

    public void testDeserialization_emptyList() {
        final Model2 model = gson.fromJson(TEST_JSON_LIST_EMPTY, Model2.class);
        assertEquals(Collections.EMPTY_LIST, model.listOfLongs);
    }

    public void testDeserialization_validListModel2() {
        final Model2 model = gson.fromJson(TEST_JSON_LIST_VALUES, Model2.class);
        try {
            model.listOfLongs.add(Long.valueOf(TEST_ANY_NUMBER));
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    private static class Model1 {

        @SerializedName("list")
        public final List<Integer> listOfIntegers;

        // Not used in testing, but needed because of final.
        public Model1(List<Integer> listOfLongs) {
            this.listOfIntegers = listOfLongs;
        }
    }

    private static class Model2 {

        @SerializedName("list")
        public final List<Long> listOfLongs;

        // Not used in testing, but needed because of final.
        public Model2(List<Long> listOfLongs) {
            this.listOfLongs = listOfLongs;
        }
    }
}
