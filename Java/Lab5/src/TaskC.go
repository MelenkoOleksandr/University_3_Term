package main

import (
	"fmt"
	"math/rand"
	"sync"
)

func getSum(arr []int) int {
	sum := 0
	for i := 0; i < len(arr); i++ {
		sum = sum + arr[i]
	}
	return sum
}

func changeValue(arr1 []int) {
	index := rand.Intn(len(arr1))
	add := rand.Float32() < 0.5

	if add {
		arr1[index] = arr1[index] + 1
	} else {
		arr1[index] = arr1[index] - 1
	}
}

func arraySumChecker(arr1, arr2, arr3 []int, checkerArray int, wg *sync.WaitGroup) {
	defer wg.Done()
	sum1 := getSum(arr2)
	sum2 := getSum(arr3)
	sum3 := getSum(arr1)

	if checkerArray == 1 {
		if sum1 != sum2 {
			changeValue(arr1)
		}

		if sum1 != sum3 {
			changeValue(arr1)
		}
	} else if checkerArray == 2 {
		if sum2 != sum1 {
			changeValue(arr2)
		}

		if sum2 != sum3 {
			changeValue(arr2)
		}
	} else {
		if sum3 != sum1 {
			changeValue(arr3)
		}

		if sum3 != sum2 {
			changeValue(arr3)
		}
	}

}

func arrayIsEqual(arr1, arr2, arr3 []int) bool {
	return getSum(arr2) == getSum(arr1) && getSum(arr2) == getSum(arr3)
}

func main() {
	wg := sync.WaitGroup{}
	arr1 := []int{4, 5, 1, 3}
	arr2 := []int{2, 100, 8, -2}
	arr3 := []int{0, 3, 10, -1}

	for !arrayIsEqual(arr1, arr2, arr3) {
		for i := 0; i < 3; i++ {
			wg.Add(1)
			go arraySumChecker(arr1, arr2, arr3, i+1, &wg)
		}
		fmt.Printf("Sums: [%d, %d, %d]\n", getSum(arr1), getSum(arr2), getSum(arr3))
		wg.Wait()
	}

}
