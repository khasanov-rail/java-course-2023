package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
import java.util.Set;

public class AnimalTasksTest {
    @Test
    @DisplayName("Тест на задачу 1")
    void sortAnimalsByHeight_shouldSortAnimalsCorrectly() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        List<Animal> sortedAnimals = AnimalTasks.sortAnimalsByHeight(animals);

        // Assert
        assertEquals(List.of(bird, cat, dog), sortedAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 2")
    void sortAnimalsByWeightAndSelectTopK_shouldSortAndSelectCorrectly() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        List<Animal> topAnimals = AnimalTasks.sortAnimalsByWeightAndSelectTopK(animals, 2);

        // Assert
        assertEquals(List.of(dog, cat), topAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 3")
    void countAnimalsByType_shouldCountCorrectly() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird, cat);

        // Act
        Map<Animal.Type, Integer> typeCount = AnimalTasks.countAnimalsByType(animals);

        // Assert
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.DOG, 1,
            Animal.Type.BIRD, 1
        );
        assertEquals(expected, typeCount);
    }

    @Test
    @DisplayName("Тест на задачу 4")
    void getAnimalWithLongestName_shouldReturnCorrectAnimal() {
        // Arrange
        Animal cat = new Animal("Kitty", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Bulldog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        Animal animalWithLongestName = AnimalTasks.getAnimalWithLongestName(animals);

        // Assert
        assertEquals(dog, animalWithLongestName);
    }

    @Test
    @DisplayName("Тест на задачу 5")
    void getMoreFrequentSex_shouldReturnCorrectSex() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird, cat);

        // Act
        Animal.Sex moreFrequentSex = AnimalTasks.getMoreFrequentSex(animals);

        // Assert
        assertEquals(Animal.Sex.F, moreFrequentSex);
    }

    @Test
    @DisplayName("Тест на задачу 6")
    void getHeaviestAnimalByType_shouldReturnCorrectAnimals() {
        // Arrange
        Animal cat1 = new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal cat2 = new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 4, 30, 7, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat1, cat2, dog, bird);

        // Act
        Map<Animal.Type, Animal> heaviestAnimals = AnimalTasks.getHeaviestAnimalByType(animals);

        // Assert
        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.CAT, cat2,
            Animal.Type.DOG, dog,
            Animal.Type.BIRD, bird
        );
        assertEquals(expected, heaviestAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 7")
    void getKthOldestAnimal_shouldReturnCorrectAnimal() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        Animal secondOldestAnimal = AnimalTasks.getKthOldestAnimal(animals, 2);

        // Assert
        assertEquals(cat, secondOldestAnimal);
    }

    @Test
    @DisplayName("Тест на задачу 8")
    void getHeaviestAnimalBelowHeight_shouldReturnCorrectAnimal() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        Optional<Animal> heaviestAnimal = AnimalTasks.getHeaviestAnimalBelowHeight(animals, 30);

        // Assert
        assertTrue(heaviestAnimal.isPresent());
        assertEquals(cat, heaviestAnimal.get());
    }

    @Test
    @DisplayName("Тест на задачу 9")
    void getTotalNumberOfPaws_shouldReturnCorrectSum() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        int totalPaws = AnimalTasks.getTotalNumberOfPaws(animals);

        // Assert
        assertEquals(10, totalPaws); // Cat and dog have 4 paws each, bird has 2 paws.
    }

    @Test
    @DisplayName("Тест на задачу 10")
    void getAnimalsWithMismatchedAgeAndPaws_shouldReturnCorrectAnimals() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        List<Animal> mismatchedAnimals = AnimalTasks.getAnimalsWithMismatchedAgeAndPaws(animals);

        // Assert
        assertEquals(List.of(dog), mismatchedAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 11")
    void getBitingAnimalsAboveHeight_shouldReturnCorrectAnimals() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, true);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        List<Animal> bitingAnimals = AnimalTasks.getBitingAnimalsAboveHeight(animals, 100);

        // Assert
        assertEquals(List.of(dog), bitingAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 12")
    void countAnimalsWithWeightGreaterThanHeight_shouldReturnCorrectCount() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 60, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        long count = AnimalTasks.countAnimalsWithWeightGreaterThanHeight(animals);

        // Assert
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Тест на задачу 13")
    void getAnimalsWithNamesMoreThanTwoWords_shouldReturnCorrectAnimals() {
        // Arrange
        Animal cat = new Animal("Cat the Great", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 60, true);
        Animal bird = new Animal("Beautiful Bird in Sky", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        List<Animal> animalsWithLongNames = AnimalTasks.getAnimalsWithNamesMoreThanTwoWords(animals);

        // Assert
        assertEquals(List.of(cat, bird), animalsWithLongNames);
    }

    @Test
    @DisplayName("Тест на задачу 14")
    void isThereADogTallerThan_shouldReturnTrueIfDogIsTaller() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 60, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        boolean result = AnimalTasks.isThereADogTallerThan(animals, 50);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Тест на задачу 14: дополнительный тест")
    void isThereADogTallerThan_shouldReturnFalseIfNoDogIsTaller() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 40, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat, dog, bird);

        // Act
        boolean result = AnimalTasks.isThereADogTallerThan(animals, 50);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Тест на задачу 15")
    void getWeightByTypeForAgeRange_shouldReturnCorrectWeightSum() {
        // Arrange
        Animal catYoung = new Animal("Young Cat", Animal.Type.CAT, Animal.Sex.F, 2, 25, 5, false);
        Animal catOld = new Animal("Old Cat", Animal.Type.CAT, Animal.Sex.M, 7, 30, 7, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 3, 10, 1, false);
        List<Animal> animals = List.of(catYoung, catOld, dog, bird);

        // Act
        Map<Animal.Type, Integer> weightSum = AnimalTasks.getWeightByTypeForAgeRange(animals, 3, 6);

        // Assert
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.DOG, 10,
            Animal.Type.BIRD, 1
        );
        assertEquals(expected, weightSum);
    }

    @Test
    @DisplayName("Тест на задачу 16")
    void sortAnimals_shouldSortCorrectly() {
        // Arrange
        Animal cat1 = new Animal("Cat A", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false);
        Animal cat2 = new Animal("Cat B", Animal.Type.CAT, Animal.Sex.M, 2, 30, 6, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        List<Animal> animals = List.of(cat2, dog, bird, cat1);

        // Act
        List<Animal> sortedAnimals = AnimalTasks.sortAnimals(animals);

        // Assert
        List<Animal> expected = List.of(bird, cat1, cat2, dog);
        assertEquals(expected, sortedAnimals);
    }

    @Test
    @DisplayName("Тест на задачу 17")
    void doSpidersBiteMoreOftenThanDogs_shouldReturnTrueIfSpidersBiteMore() {
        // Arrange
        Animal spider1 = new Animal("Spider 1", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 1, true);
        Animal spider2 = new Animal("Spider 2", Animal.Type.SPIDER, Animal.Sex.M, 1, 6, 1, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        List<Animal> animals = List.of(spider1, spider2, dog);

        // Act
        boolean result = AnimalTasks.doSpidersBiteMoreOftenThanDogs(animals);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Тест на задачу 17: дополнительный тест")
    void doSpidersBiteMoreOftenThanDogs_shouldReturnFalseIfDogsBiteMore() {
        // Arrange
        Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 1, true);
        Animal dog1 = new Animal("Dog 1", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true);
        Animal dog2 = new Animal("Dog 2", Animal.Type.DOG, Animal.Sex.F, 3, 45, 9, false);
        List<Animal> animals = List.of(spider, dog1, dog2);

        // Act
        boolean result = AnimalTasks.doSpidersBiteMoreOftenThanDogs(animals);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Тест на задачу 18")
    void findHeaviestFishInLists_shouldReturnHeaviestFish() {
        // Arrange
        Animal fish1 = new Animal("Fish 1", Animal.Type.FISH, Animal.Sex.F, 1, 10, 2, false);
        Animal fish2 = new Animal("Fish 2", Animal.Type.FISH, Animal.Sex.M, 2, 12, 3, false);
        Animal fish3 = new Animal("Fish 3", Animal.Type.FISH, Animal.Sex.F, 1, 11, 1, false);
        List<Animal> list1 = List.of(fish1, fish2);
        List<Animal> list2 = List.of(fish3);
        List<List<Animal>> listOfLists = List.of(list1, list2);

        // Act
        Animal result = AnimalTasks.findHeaviestFishInLists(listOfLists);

        // Assert
        assertEquals(fish2, result);
    }

    @Test
    @DisplayName("Тест на задачу 19")
    void findAnimalsWithErrors_shouldReturnAnimalsWithErrors() {
        // Arrange
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, -1, 20, 4, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, -30, 10, true);
        List<Animal> animals = List.of(cat, dog);

        // Act
        Map<String, Set<AnimalTasks.ValidationError>> result = AnimalTasks.findAnimalsWithErrors(animals);

        // Assert
        Map<String, Set<AnimalTasks.ValidationError>> expected = Map.of(
            "Cat", Set.of(AnimalTasks.ValidationError.NEGATIVE_AGE),
            "Dog", Set.of(AnimalTasks.ValidationError.NEGATIVE_HEIGHT)
        );
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Тест на задачу 20")
    void formatAnimalErrors_shouldFormatErrors() {
        // Arrange
        Map<String, Set<AnimalTasks.ValidationError>> errors = Map.of(
            "Cat", Set.of(AnimalTasks.ValidationError.NEGATIVE_AGE),
            "Dog", Set.of(AnimalTasks.ValidationError.NEGATIVE_HEIGHT, AnimalTasks.ValidationError.NEGATIVE_WEIGHT)
        );

        // Act
        Map<String, String> result = AnimalTasks.formatAnimalErrors(errors);

        // Assert
        Map<String, String> expected = Map.of(
            "Cat", "Negative Age",
            "Dog", "Negative Height, Negative Weight"
        );
        assertEquals(expected, result);
    }
}
