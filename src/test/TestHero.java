package test;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import playground.Area;
import playground.Item;

import businesslogic.Hero;

/**
 * TestHero.java
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class TestHero implements Observer{

	Hero held = new Hero("derHeld", null);
	boolean end = false;
	
	@Test
	public void Semestertest() {
		assertEquals(0, held.getCreditPoints());
		assertEquals(1, held.getSemester());
	}
	
	@Test
	public void Semester2Test(){
		held.addPoints(50);
		assertEquals(2, held.getSemester());
	}
	@Test
	public void SemesterBack(){
		held.addPoints(-51);
		assertEquals(1, held.getSemester());
	}
	
	@Test
	public void ItemTest(){
		assertTrue(held.setAuswahl(0));				// Ist Backpack leer?
		Item newitem = new Item("eins", null, 0); 	
		
		assertTrue(held.addItemToBackpack(newitem));
		assertFalse(held.setAuswahl(0));	
	}

	
	@Test
	public void BackPackCapacityTest(){
		Item[] newItem = new Item[10];
		for (int i=0; i <10; i++ ){
			newItem[i] = new Item(String.valueOf(i), null,0);
			held.addItemToBackpack(newItem[i]);
		}//Mehr als 10 Elemente im Rucksack, kann nicht mehr rein
		assertFalse(held.addItemToBackpack(newItem[0]));
	}
	
	@Test
	public void SkipTest(){
		for (int i=0; i <10; i++ ){
			held.addItemToBackpack(new Item(String.valueOf(i), null,0));
		}//Mehr als 10 Elemente im Rucksack, kann nicht mehr rein
		held.setAuswahl(0);
		assertEquals(0, held.getAuswahl());
		held.nextItem();
		assertEquals(1, held.getAuswahl());
		for(int i=0; i<9; i++){
			held.nextItem();
		}
		assertEquals(0, held.getAuswahl());
	}
	
	@Test
	public void DropTest(){
		Area area = new Area(null, null, null ,null, null, null, null, null);
		held.addItemToBackpack(new Item("item", null,0));
		held.setAuswahl(0);
		assertEquals(1, held.getBackpack().size());
		assertTrue(held.drop(area));
		assertEquals(0, held.getBackpack().size());
		
	}
	
	@Test
	public void EndeTest(){
		held.addObserver(this);
		assertFalse(end);
		for (int i=0; i<6; i++){
			held.addPoints(50);
		}
		try {
			Thread.sleep(4010);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(end);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String){
			if("Spielende".equals((String)arg1)){
				end = true;
			}
		}
	}
	
}
