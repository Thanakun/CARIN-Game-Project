import { Store } from "pullstate"

type TypemapStore = {
    map: string[][]
    x : number
    y : number
}

export const MapStore = new Store<TypemapStore>({
    map: [],
    x: 0,
    y: 0
})

export const getMap = (x : number, y : number) => {
    const newmap : string[][] = new Array(y)
    for(let i=0;i<y;i++) {
        newmap[i] = new Array(x)
    }
    for (let i=0;i<y;i++) {
        for (let j=0;j<x;j++) {
            newmap[i][j] = (Math.floor(Math.random() * 5) > 0? 'G' : 'R')
        }
    }
    console.log(newmap)
    MapStore.update(
        s => {s.map = newmap}
    )
}



