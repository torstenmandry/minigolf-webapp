package de.javandry.minigolf.webapp

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class BallController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ballInstanceList: Ball.list(params), ballInstanceTotal: Ball.count()]
    }

    @Secured(['ROLE_USER'])
    def create() {
        [ballInstance: new Ball(params)]
    }

    def save() {
        def ballInstance = new Ball(params)
        if (!ballInstance.save(flush: true)) {
            render(view: "create", model: [ballInstance: ballInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ball.label', default: 'Ball'), ballInstance.id])
        redirect(action: "list")
    }

    def show(Long id) {
        def ballInstance = Ball.get(id)
        if (!ballInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ball.label', default: 'Ball'), id])
            redirect(action: "list")
            return
        }

        [ballInstance: ballInstance]
    }

    def edit(Long id) {
        def ballInstance = Ball.get(id)
        if (!ballInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ball.label', default: 'Ball'), id])
            redirect(action: "list")
            return
        }

        [ballInstance: ballInstance]
    }

    def update(Long id, Long version) {
        def ballInstance = Ball.get(id)
        if (!ballInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ball.label', default: 'Ball'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ballInstance.version > version) {
                ballInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ball.label', default: 'Ball')] as Object[],
                        "Another user has updated this Ball while you were editing")
                render(view: "list", model: [ballInstance: ballInstance])
                return
            }
        }

        ballInstance.properties = params

        if (!ballInstance.save(flush: true)) {
            render(view: "edit", model: [ballInstance: ballInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ball.label', default: 'Ball'), ballInstance.id])
        redirect(action: "list")
    }

    def delete(Long id) {
        def ballInstance = Ball.get(id)
        if (!ballInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ball.label'), id])
            redirect(action: "list")
            return
        }

        try {
            ballInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ball.label'), id])
            redirect(action: "list")
        } catch (DataIntegrityViolationException ignored) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ball.label'), id])
            redirect(action: "edit", id: id)
        }
    }
}
