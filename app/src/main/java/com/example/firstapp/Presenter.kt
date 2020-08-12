package com.example.firstapp

class Presenter(private val view: MainContract.MainView, private val model: Model):
    MainContract.MainPresenter {

    override fun add() {
        model.addToList(view.getStringFromEditText())
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton()
        }
        view.showStringToTextView()
        view.clearEditText()
        view.updateSortButtonAndRadioGroup(model.isListSorted())
    }

    override fun sort() {
        model.initSortedStringAndTimeStamps(view.getSortType())
        view.showSortedStringWithTimeStamps(
            model.parseDateToString(model.startSortingTime),
            model.sortedString,
            model.parseDateToString(model.endSortingTime),
            model.getDateDifference(model.endSortingTime, model.startSortingTime)
        )
        view.updateSortButtonAndRadioGroup(false)
    }

    override fun clear() {
        model.clearStringList()
        view.clearTextView()
        view.updateSortButtonAndRadioGroup(false)
    }

    fun isEditViewEmpty(string: String) {
        if (string.isEmpty()) {
            view.toggleAddButton(false)
        } else {
            view.toggleAddButton(true)
        }
    }
}