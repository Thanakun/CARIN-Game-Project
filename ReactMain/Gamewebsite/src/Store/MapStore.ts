import { Store } from "pullstate"

type TypemapStore = {
    map: string[][]
    x : number
    y : number
    max_scale : number
}

export const MapStore = new Store<TypemapStore>({
    map: [],
    x: 0,
    y: 0,
    max_scale: 0
})

export const getMap = (x : number, y : number) => {
    const newmap : string[][] = new Array(y)
    for(let i=0;i<y;i++) {
        newmap[i] = new Array(x)
    }
    for (let i=0;i<y;i++) {
        for (let j=0;j<x;j++) {
            newmap[i][j] = (Math.floor(Math.random() * 5) > 0? 'W' : 'WS')
        }
    }
    let newmax_scale = 0
    let x_scale : number = 1000/x
    let y_scale : number = 660/y
    if (y<x) {
        if(y_scale*x <= 1000) newmax_scale = y_scale
        else newmax_scale = x_scale
    }else {
        if(x_scale*x <= 660) newmax_scale = x_scale
        else newmax_scale = y_scale
    }

    MapStore.update(
        s => {
            s.map = newmap
            s.max_scale = newmax_scale
        }
    )
}



