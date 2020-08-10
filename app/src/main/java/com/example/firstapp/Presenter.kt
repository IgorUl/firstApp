package com.example.firstapp

class Presenter(private val view: MainContract.MainView): MainContract.MainPresenter {
    private val model: Model = Model()


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
        val sortedStringWithTime:String = model.getStringSortingBy(view.getSortType())
        view.showStringToTextView(sortedStringWithTime)
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