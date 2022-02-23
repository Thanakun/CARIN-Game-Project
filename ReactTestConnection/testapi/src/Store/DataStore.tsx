import {Store} from 'pullstate'
import MainMenu from '../Components/MainMenu'
import { OrganismType, TimerType } from '../Components/Playing'


export type DataStoreType = {
    textureMap: string[][]
    organism: OrganismType[][]
    max_x : number 
    max_y : number 
    timer : TimerType
    credit : number
    gameState: string
}


export const DataStore = new Store<DataStoreType>({
    textureMap: [],
    organism: [],
    max_x: -1,
    max_y: -1,
    timer : {
        time_count : 0,
        timePass:1000
    },
    credit : 0,
    gameState: "MainMenu"
})

export const initMap = (x : number, y : number) => {
    const newmap : string[][] = new Array(y)
    const organmap:OrganismType[][] = new Array(y)

    for(let i=0;i<y;i++) {
        newmap[i] = new Array(x)
        organmap[i] = new Array(x)
    }
    for (let i=0;i<y;i++) {
        for (let j=0;j<x;j++) {
            newmap[i][j] = (Math.floor(Math.random() * 5) > 0? 'G' : 'R')
        }
    }
    console.log(newmap)
    DataStore.update(
        s => {
            s.textureMap = newmap
            s.organism = organmap
        }
    )
}
