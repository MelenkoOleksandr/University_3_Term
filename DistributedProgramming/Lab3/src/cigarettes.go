package main

import (
	"math/rand"
	"sync"
)

func contains(slice []string, elem string) bool {
	for i := 0; i < len(slice); i++ {
		if slice[i] == elem {
			return true
		}
	}
	return false
}

func agentIngredients() []string {
	ingredientsList := []string{"matches", "tobacco", "paper"}
	first := rand.Intn(3)
	second := rand.Intn(3)
	for first == second {
		second = rand.Intn(3)
	}
	print("Agent puts: " + ingredientsList[first] + " " + ingredientsList[second] + "\n")
	return []string{ingredientsList[first], ingredientsList[second]}
}

func smoker(ingredient string, ingredients []string, wg *sync.WaitGroup, cigaretteReady *bool) {
	if !contains(ingredients, ingredient) && !*cigaretteReady {
		ingredients = append(ingredients, ingredient)
		println("Smoker added " + ingredient)
		println("Finished cigarette\n\n")
		ingredients = []string{}
		*cigaretteReady = true
	}
	wg.Done()
}

func main() {
	var ingredients []string
	var wg = sync.WaitGroup{}
	var mut = sync.Mutex{}

	for {
		cigaretteReady := false
		mut.Lock()

		wg.Add(1)
		go func() {
			ingredients = agentIngredients()
			wg.Done()
		}()
		wg.Wait()

		wg.Add(3)
		go smoker("matches", ingredients, &wg, &cigaretteReady)
		go smoker("tobacco", ingredients, &wg, &cigaretteReady)
		go smoker("paper", ingredients, &wg, &cigaretteReady)
		wg.Wait()

		mut.Unlock()
	}

}
