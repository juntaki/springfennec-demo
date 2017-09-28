package com.juntaki.springfennecdemo.application

import com.juntaki.springfennec.annotation.ApiGroup
import com.juntaki.springfennec.annotation.ApiGroups
import io.swagger.annotations.Info
import io.swagger.annotations.SwaggerDefinition
import org.springframework.web.bind.annotation.*

@ApiGroups(
        arrayOf(
                ApiGroup(arrayOf("^/demo/user.*"),
                        name = "user",
                        apiInfo = SwaggerDefinition(
                                info = Info(
                                        version = "1.0",
                                        title = "User API"
                                ),
                                host = "localhost:8080",
                                schemes = arrayOf(SwaggerDefinition.Scheme.HTTP)
                        )
                ),
                ApiGroup(arrayOf("^/demo/admin.*"),
                        name = "admin",
                        apiInfo = SwaggerDefinition(
                                info = Info(
                                        version = "1.0",
                                        title = "Admin API with secret info"
                                ),
                                host = "localhost:8080",
                                schemes = arrayOf(SwaggerDefinition.Scheme.HTTP)
                        )
                ),
                ApiGroup(arrayOf(".*"),
                        name = "springfennec",
                        apiInfo = SwaggerDefinition(
                                info = Info(
                                        version = "1.0",
                                        title = "All API for compareing with Springfox output"
                                ),
                                host = "localhost:8080",
                                schemes = arrayOf(SwaggerDefinition.Scheme.HTTP)
                        )
                )
        )
)
@RestController
@RequestMapping("/demo")
class DemoController {
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Int): UserApiParam = UserApiParam()

    @PostMapping("/user")
    fun createUser(@RequestBody param: UserApiParam): Boolean = true

    @GetMapping("/admin/user/{id}")
    fun getUserByAdmin(@PathVariable id: Int): AdminApiParam = AdminApiParam()

    @PostMapping("/admin/user")
    fun createUserByAdmin(@RequestBody param: AdminApiParam): Boolean = true
}

class UserApiParam(
        val info: Info = Info("desert")
) {
    data class Info(val region: String)
}

class AdminApiParam(
        val info: Info = Info("secret", "desert")
) {
    class Info(
            val secretInfo: String,
            val region: String
    )
}
