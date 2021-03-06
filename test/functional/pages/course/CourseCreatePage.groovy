package pages.course

class CourseCreatePage extends AbstractCoursePage {

    static at = { title.startsWith "New Course" }

    static content = {
        errors { $("ul.messages.error") }
        name { $("input#name") }
        type { $("select#type") }
        address { $("textarea#address") }
        createButton { $("input#create") }
        cancelButton { $("a#cancel") }
    }

    String errorForField(String fieldName) {
        return errors.find("li", "data-field-id": fieldName)?.text()?.trim()
    }

    void create(def args) {
        name.value(args.name)
        type.value(args.type)
        address.value(args.address)
        createButton.click()
    }

    void cancel() {
        cancelButton.click()
    }
}
