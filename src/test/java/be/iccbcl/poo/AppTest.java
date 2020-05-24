package be.iccbcl.poo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import be.iccbxl.poo.data.DataFileTest;
import be.iccbxl.poo.entities.BookTest;
import be.iccbxl.poo.entities.GraphicNovelTest;
import be.iccbxl.poo.entities.OnlineBook;
import be.iccbxl.poo.entities.OnlineBookTest;
import be.iccbxl.poo.entities.PersonTest;
import be.iccbxl.poo.logic.Logic;
import be.iccbxl.poo.logic.LogicTest;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
   DataFileTest.class ,BookTest.class, GraphicNovelTest.class, OnlineBookTest.class, PersonTest.class, LogicTest.class
})

/**
 * Lance les tests unitaires.
 * @author Marcel F.
 */
public class AppTest {
}

