package com.example.firstapp

class Presenter(private val view: MainContract.MainView, private val model: Model) :
    MainContract.MainPresenter {


    override fun add() {
        model.addToList(view.getStringFromEditText())
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton()
        }
        view.showStringToTextView(model.getInputString())
        view.clearEditText()
        view.updateSortButtonAndRadioGroup(model.isListSorted())
    }

    override fun sort() {
        model.initTimeStamps(view.getSortType())
        view.showSortedStringWithTimeStamps(
            model.parseDateToString(model.startSortingTime),
            model.sortedString,
            model.parseDateToString(model.endSortingTime),
            model.getDateDifference(model.endSortingTime, model.startSortingTime)
        )
        view.updateSortButtonAndRadioGroup(false)
        view.changeButtonColor()

    }

    override fun clear() {
        model.clearStringList()
        view.clearTextView()
        view.updateSortButtonAndRadioGroup(false)
        view.changeButtonColor()
    }


}