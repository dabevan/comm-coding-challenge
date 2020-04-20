val complexOpeningHoursSpecification = """{
  "openingHoursSpecification":
    [
        {
           "dayOfWeek":[
              "Monday"
           ],
           "opens":"00:00",
           "closes":"00:00"
         },
        {
           "dayOfWeek":[
               "Tuesday",
               "Wednesday",
               "Thursday"
           ],
           "opens":"17:00",
           "closes":"22:00"
        },
        {
           "dayOfWeek":[
                "Wednesday",
                "Thursday"
           ],
           "opens":"12:00",
            "closes":"14:00"
        },
        {
           "dayOfWeek":[
                "Friday",
                "Saturday"
           ],
           "opens":"12:00",
           "closes":"22:30"
         },
         {
            "dayOfWeek":[
                "Sunday"
            ],
            "opens":"12:00",
            "closes":"17:00"
          }
    ]
}""".trimIndent()

val simpleOpeningHoursSpecification = """{
    "openingHoursSpecification":
    [    
        {
            "dayOfWeek":[
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
            ],
            "opens":"12:00",
            "closes":"22:00"
        } ,
        {
            "dayOfWeek":[
                "Saturday",
                "Sunday"
            ],
            "opens":"12:00",
            "closes":"23:00"
        }
    ]
}""".trimIndent()
