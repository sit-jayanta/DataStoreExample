**Data Store Example**

>This is a small demo for datastore android.

This demo is to understand and learn the use of both preference and proto datastore.
In this demo we have saved username and password using preference datastore and email with proto datastore.



![Home Screen](https://github.com/sit-jayanta/DataStoreExample/assets/154864948/0d825b7d-79a3-484f-ae20-51a4e3faa7b6)
![Login Screen](https://github.com/sit-jayanta/DataStoreExample/assets/154864948/c571a711-3cfa-4869-8989-7badde1a3497)

**Dependencies** 

For Both-
`implementation("androidx.datastore:datastore:1.0.0")`

For Preference datastore
`implementation("androidx.datastore:datastore-preferences:1.0.0")`

For proto datastore
`plugins{
id ("com.google.protobuf") version "0.9.1"
}`

`dependencies{
implementation("com.google.protobuf:protobuf-javalite:4.26.0-RC2")
}
`
`protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.2"
    }
    plugins {
        id("javalite") { artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0" }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
            task.plugins{
            }
        }
    }
}`

