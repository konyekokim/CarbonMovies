include(
        ":app",
        "core",
        "lib:commons",
        "lib:navigation",
        ":lib:resources",
        ":lib:testcommons",
        ":feature:movies",
        ":feature:moviedetail"
)
rootProject.name = "Carbon Movies"
include(":moviedetail")
