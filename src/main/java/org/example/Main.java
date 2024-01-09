package org.example;

import org.example.datatypes.*;

public class Main {
    public static void main(String[] args) throws Exception {

//        testTypeString();
//        testTypeList();
//        testTypeSets();
//        testTypehashes();
//        testSortedSets();
        testTypeStream();
    }

    public static void testTypeString() throws Exception {
        TypeString typeString = new TypeString();

//        typeString.setAndGet();
//        typeString.setWithOptions();
//        typeString.setWithExpire();
//        typeString.getSet();
//        typeString.mSetAndMGet();
//        typeString.stringAsCounter();
//        typeString.delete();
    }

    public static void testTypeList() {
        TypeList list = new TypeList();
//        list.pushAndPop();
//        list.popAndPushToAnother();
//        list.limitTheLength();
//        list.testListAddingSpeed();
//        list.extractRangesOfElements();
        list.blockingOperation();
    }

    public static void testTypeSets() {
        TypeSets sets = new TypeSets();

//        sets.setsAdd();
//        sets.getAllMembersOfASet();
//        sets.isMembers();
//        sets.findSameValueIncludeInTwoSets();
//        sets.getTotalMemberOfASet();
    }

    public static void  testTypehashes() {
        TypeHashes hashes = new TypeHashes();

//        hashes.hsetAndhget();
//        hashes.hmget();
//        hashes.increaseBy();
    }

    public static void testSortedSets(){
        TypeSortedSets sortedSets = new TypeSortedSets();
//        sortedSets.zAdd();
//        sortedSets.getSortedSets();
//        sortedSets.getScoreByMember();
//        sortedSets.lexicographicalScore();
//        sortedSets.updateScore();
    }

    public static void testTypeStream() {
        TypeStreams streams = new TypeStreams();
//        streams.add();
//        streams.getByEntryId();
        streams.xRead();
    }
}