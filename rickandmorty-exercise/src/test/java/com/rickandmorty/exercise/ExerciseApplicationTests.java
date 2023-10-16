package com.rickandmorty.exercise;

import com.rickandmorty.exercise.controllers.CharacterRestController;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	private CharacterRestController characterRestController;

	@Test
	void contextLoads() {
		Assert.notNull(characterRestController, "Error getting OperationRestController");
	}

}
