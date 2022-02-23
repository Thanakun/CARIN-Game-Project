console.log("as")
const btn = document.querySelector(".btn") 
    console.log(btn)
    btn.onmousemove = function(e) {
        const x = e.pageX - btn.offsetLeft
        const y = e.pageY - btn.offsetTop

        btn.styls.setProperty('--x', x + 'px')
        btn.styls.setProperty('--y', y + 'px')
    }