package test;

import static org.junit.Assert.*;

import org.junit.Test;

import playground.MapBuilder;
import businesslogic.GameEngine;
import businesslogic.Hero;

/**
 * TestGameEngine.java
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class TestGameEngine {

	
	Hero hero = new Hero("TheHero", null);
	
	MapBuilder mapBuilder = new MapBuilder();

	GameEngine gameEngine = new GameEngine(mapBuilder, hero);
	
	
	
	@Test
	public void MoveForwardTest() {
		gameEngine.move(1);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		assertEquals(0.2, gameEngine.getPosZ(), 0.01);
		assertEquals(0, gameEngine.getPosX(), 0.01);	
	}
	
	@Test
	public void MoveBackTest() {
		gameEngine.move(-1);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		assertEquals(-0.2, gameEngine.getPosZ(), 0.01);
		assertEquals(0, gameEngine.getPosX(), 0.01);	
	}
	
	
	@Test
	public void RotateTest() {	
		gameEngine.rotate(1);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(Math.PI*0.1f, gameEngine.getOrientation(), 0.01);	
	}
	
	@Test
	public void RotateBackTest() {	
		gameEngine.rotate(-1);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(-Math.PI*0.1f, gameEngine.getOrientation(), 0.01);	
	}
	
	@Test
	public void InteractTest() {
		
		for(int i=0; i<60; i++){
			gameEngine.move(1);
			gameEngine.setMoving(false);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		assertEquals(12, gameEngine.getPosZ(), 0.01);
		
		
		for(int i=0; i<4; i++){
			gameEngine.rotate(1);
			gameEngine.setRotating(false);
			try {
				Thread.sleep(125);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		assertEquals(Math.PI*0.4f, gameEngine.getOrientation(), 0.01);	
		
		
		for(int i=0; i<10; i++){
			gameEngine.move(1);
			gameEngine.setMoving(false);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		
		assertEquals("Vorstellungsgespraech", gameEngine.getMap().getAreaByCoordinates(gameEngine.getPosX(), gameEngine.getPosZ()).getQuest().getQuestName());

		gameEngine.interact();
		
		assertEquals(1, gameEngine.getActiveQuests().size());
		
		gameEngine.reply(1);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		gameEngine.reply(1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		System.out.println();
		assertEquals(0, gameEngine.getActiveQuests().size());
		

		
	}

}
