package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)


type abn struct {
	a     uint64
	b     uint64
	angle int
}

func tranf(k abn, com []uint64) (uint64, uint64) {
	var a, b uint64
	switch k.angle {
	case 0:
		a = com[0] + k.a - 1
		b = com[1] + k.b - 1
		break
	case 1:

		a = com[0] + k.b - 1
		b = com[2] + com[1] - k.a + 1

		break
	case 2:
		a = com[2] + com[0] - k.a + 1
		b = com[2] + com[1] - k.b + 1
		break

	case 3:
		a = com[2] + com[0] - k.b + 1
		b = com[1] + k.a - 1

		break
	}
	return a, b
}
func analyse(k abn, com []uint64, c uint64) (bool, uint64, uint64) {

	var a, b uint64
	a = k.a - com[0]
	b = k.b - com[1]

	if a <= 0 || b <= 0 || a > c+1 || b > c+1 {
		return true, a, b
	}

	return false, a, b
}
func kingRichardKnights(n uint64, s uint64, knights []uint64, com [][]uint64) [][]uint64 {

	pos := make([]abn, len(knights))
	result := make([][]uint64, len(knights))
	tcom := make([][]uint64, len(com))
	incom := []uint64{0, 0}
	dcom := []uint64{1, 1, n}
	/////
	for i := 0; i < len(knights); i++ {
		pos[i].a = (knights[i] / n) + 1
		pos[i].b = (knights[i] % n) + 1

	}

	for i := 0; i < len(com); i++ {
		var x, y, ksx, ksy, res1, res2 uint64

		if i != 0 {
			x = com[i][0] - com[i-1][0]
			y = com[i][1] - com[i-1][1]
			ksx = com[i-1][2] - com[i][2] - x
			ksy = com[i-1][2] - com[i][2] - y
			switch i % 4 {
			case 0:
				res1 = tcom[i-1][0] + x
				res2 = tcom[i-1][1] + y
			case 1:
				res1 = tcom[i-1][0] + ksy
				res2 = tcom[i-1][1] + x
			case 2:
				res1 = tcom[i-1][0] + ksx
				res2 = tcom[i-1][1] + ksy
			case 3:
				res1 = tcom[i-1][0] + y
				res2 = tcom[i-1][1] + ksx
			}
		} else {
			x = com[i][0] - 1
			y = com[i][1] - 1
			ksx = n - com[i][2] - x
			ksy = n - com[i][2] - y

			res1 = x
			res2 = y
		}
		tcom[i] = append(tcom[i], res1)
		tcom[i] = append(tcom[i], res2)

	}
	/////////
	for ii := 0; ii < len(pos); ii++ {

		gr := len(com) //
		sm := 0
		sgg := -1

		for g := 0; g < 30; g++ {
			oi := true
			ss := (gr + sm) / 2
			sd, _, _ := analyse(pos[ii], tcom[ss], com[ss][2])

			if sd == true {

				gr = ss
				sgg = ss

			} else {
				sm = ss

			}
			if ss == 0 || ss == len(com)-1 {
				if oi == false {
					break
				}

				oi = false
			}

		}
		///
		lenc := len(com) - 1

		if sgg == 0 {
			_, pos[ii].a, pos[ii].b = analyse(pos[ii], incom, n)
			pos[ii].angle = 0
		} else if sgg == -1 {
			_, pos[ii].a, pos[ii].b = analyse(pos[ii], tcom[lenc], com[lenc][2])
			pos[ii].angle = (len(com)) % 4

		} else {
			_, pos[ii].a, pos[ii].b = analyse(pos[ii], tcom[sgg-1], com[sgg-1][2])
			pos[ii].angle = (sgg) % 4
		}

		var a, b uint64



		if sgg == 0 {

			a, b = tranf(pos[ii], dcom)
		} else if sgg == -1 {
			a, b = tranf(pos[ii], com[lenc])
		} else {
			a, b = tranf(pos[ii], com[sgg-1])
		}
		result[ii] = make([]uint64, 2)
		result[ii][0] = a
		result[ii][1] = b


	}

	return result
}

func main() {
	var knights []uint64
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	writer := bufio.NewWriterSize(os.Stdout, 1024*1024)

	nTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
	checkError(err)
	n := uint64(nTemp)

	sTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
	checkError(err)
	s := uint64(sTemp)

	var commands [][]uint64
	for commandsRowItr := 0; commandsRowItr < int(s); commandsRowItr++ {
		commandsRowTemp := strings.Split(readLine(reader), " ")

		var commandsRow []uint64
		for _, commandsRowItem := range commandsRowTemp {
			commandsItemTemp, err := strconv.ParseInt(commandsRowItem, 10, 64)
			checkError(err)
			commandsItem := uint64(commandsItemTemp)
			commandsRow = append(commandsRow, commandsItem)
		}

		if len(commandsRow) != int(3) {
			panic("Bad input")
		}

		commands = append(commands, commandsRow)
	}
	vTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
	checkError(err)
	v := uint64(vTemp)
	for i := uint64(0); i < v; i++ {

		knightstemp, err := strconv.ParseInt(readLine(reader), 10, 64)
		checkError(err)

		knights = append(knights, uint64(knightstemp))

	}
	//
	result := kingRichardKnights(n, s, knights, commands)

	for resultRowItr, rowItem := range result {
		for resultColumnItr, colItem := range rowItem {
			fmt.Fprintf(writer, "%d", colItem)

			if resultColumnItr != len(rowItem)-1 {
				fmt.Fprintf(writer, " ")
			}
		}

		if resultRowItr != len(result)-1 {
			fmt.Fprintf(writer, "\n")
		}
	}

	fmt.Fprintf(writer, "\n")

	writer.Flush()
}

func readLine(reader *bufio.Reader) string {
	str, _, err := reader.ReadLine()
	if err == io.EOF {
		return ""
	}

	return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
	if err != nil {
		panic(err)
	}
}