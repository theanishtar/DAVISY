const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const tabs = $$('.navigation-item')
const panes = $$('.js-active')

    tabs.forEach((tab, index) => {
        const pane = panes[index]

        tab.onclick = function () {
          


            $('.navigation-item.active').classList.remove('active')
            $('.js-active.active').classList.remove('active')


            this.classList.add('active')
            pane.classList.add('active')

        }
    });