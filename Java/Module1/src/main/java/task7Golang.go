package main

import (
	"container/list"
	"sync"
	"time"
)

type Train struct {
	name          int
	desiredTunnel string
}

type Tunnel struct {
	tunnelName string
	isBusy     bool
}

func tunnelManager(trains1 *list.List, trains2 *list.List, tunnels []Tunnel, wg *sync.WaitGroup) {
	for {
		if trains1.Len() == 0 {
			break
		}
		firstTrain := trains1.Front()
		trains1.Remove(firstTrain)
		if firstTrain.Value.(Train).desiredTunnel == tunnels[0].tunnelName {
			if !tunnels[0].isBusy {
				tunnels[0].isBusy = true
				println(firstTrain.Value.(Train).name, " on the tunnel 1")
				time.Sleep(5000 * time.Millisecond)
				println(firstTrain.Value.(Train).name, " exit tunnel 1")
				tunnels[0].isBusy = false
			} else {
				timer := time.NewTimer(2000 * time.Millisecond)
				<-timer.C
				newTrain := Train{firstTrain.Value.(Train).name, "Tunnel2"}
				trains2.PushBack(newTrain)
			}

		} else {
			if !tunnels[1].isBusy {
				tunnels[1].isBusy = true
				println(firstTrain.Value.(Train).name, " on the tunnel 2")
				time.Sleep(5000 * time.Millisecond)
				println(firstTrain.Value.(Train).name, " exit tunnel 2")
				tunnels[1].isBusy = false
			} else {
				timer := time.NewTimer(2000 * time.Millisecond)
				<-timer.C
				newTrain := Train{firstTrain.Value.(Train).name, "Tunnel1"}
				trains1.PushBack(newTrain)
			}
		}
		time.Sleep(1000 * time.Millisecond)
	}
	wg.Done()
}

func main() {
	//lock := sync.Mutex{}
	wg := sync.WaitGroup{}
	trains1 := list.New()
	trains2 := list.New()

	for i := 10; i < 15; i++ {
		trains1.PushBack(Train{i, "Tunnel1"})
	}

	for i := 20; i < 25; i++ {
		trains2.PushBack(Train{i, "Tunnel2"})
	}

	tunnels := []Tunnel{{"Tunnel1", false}, {"Tunnel2", false}}

	wg.Add(2)
	go tunnelManager(trains1, trains2, tunnels, &wg)
	go tunnelManager(trains2, trains1, tunnels, &wg)
	wg.Wait()
}
