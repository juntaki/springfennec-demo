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
                                )
                        )
                ),
                ApiGroup(arrayOf("^/demo/admin.*"),
                        name = "admin",
                        apiInfo = SwaggerDefinition(
                                info = Info(
                                        version = "1.0",
                                        title = "Admin API with secret info"
                                )
                        )
                ),
                ApiGroup(arrayOf(".*"),
                        name = "springfennec",
                        apiInfo = SwaggerDefinition(
                                info = Info(
                                        version = "1.0",
                                        title = "All API for compareing with Springfox output"
                                )
                        )
                )
        )
)
@RestController
@RequestMapping("/demo")
class DemoController {
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Int): userApiParam = userApiParam(userApiParam.Info("desert"))

    @PostMapping("/user")
    fun createUser(@RequestBody param: userApiParam): Boolean = true

    @GetMapping("/admin/user/{id}")
    fun getUserByAdmin(@PathVariable id: Int): adminApiParam = adminApiParam(adminApiParam.Info("secret", "desert"))

    @PostMapping("/admin/user")
    fun createUserByAdmin(@RequestBody param: adminApiParam): Boolean = true
}

class userApiParam(
        val info: Info
) {
    data class Info(val region: String)
}

class adminApiParam(
        val info: Info
) {
    class Info(
            val secretInfo: String,
            val region: String
    )
}
