package main

import (
	"fmt"
	"math/rand"
	"os"
	"sync"
)

func main() {
	garden := [5][5]bool{}
	mutex := sync.RWMutex{}
	wg := sync.WaitGroup{}

	wg.Add(4)

	go func() {
		for {
			mutex.Lock()
			for i := 0; i < len(garden); i++ {
				for j := 0; j < len(garden[i]); j++ {
					if !garden[i][j] {
						fmt.Printf("Gardener watered plant[%d][%d]\n", i, j)
						garden[i][j] = true
					}
				}
			}
			mutex.Unlock()
		}
	}()

	go func() {
		for {
			mutex.Lock()
			i := rand.Intn(5)
			j := rand.Intn(5)
			fmt.Printf("Nature killed plant[%d][%d]\n", i, j)
			garden[i][j] = false
			mutex.Unlock()
		}
	}()

	go func() {
		file, err := os.Create("garden.txt")

		if err != nil {
			fmt.Println("Unable to create file ", err)
			os.Exit(1)
		}
		defer file.Close()

		for {
			mutex.RLock()
			for i := 0; i < len(garden); i++ {
				line := ""
				for j := 0; j < len(garden[i]); j++ {
					if garden[i][j] {
						line += "1 "
					} else {
						line += "0 "
					}
				}
				file.WriteString(line + "\n")
			}
			file.WriteString("\n\n\n")
			mutex.RUnlock()
		}
	}()

	go func() {
		for {
			mutex.RLock()
			println("\nCurrent Garden state:")
			for i := 0; i < len(garden); i++ {
				for j := 0; j < len(garden[i]); j++ {
					if garden[i][j] {
						fmt.Print("1 ")
					} else {
						fmt.Print("0 ")
					}
				}
				println()
			}
			println()
			mutex.RUnlock()
		}
	}()

	wg.Wait()
}
