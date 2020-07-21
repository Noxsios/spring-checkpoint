package com.galvanize.springcheckpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @GetMapping("/camelize")
    public String getCamelize(
            @RequestParam(value = "original", required = true, defaultValue = "") String original,
            @RequestParam(value = "initialCap", defaultValue = "false") boolean initialCap) {

        StringBuilder outString = new StringBuilder();
        String[] splitUnder = original.split("_");
        for (String str : splitUnder) {
            outString.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }

        if (!initialCap) {
            return outString.substring(0, 1).toLowerCase() + outString.substring(1);
        } else {
            return String.valueOf(outString);
        }

    }

    @GetMapping("/redact")
    public String getRedact(
            @RequestParam(value = "original", required = true, defaultValue = "") String original,
            @RequestParam(value = "badWord", defaultValue = "") List<String> badWords) {
        StringBuilder outString = new StringBuilder();
        String[] splitSpace = original.split(" ");

        for (String str : splitSpace) {
            for (String inner : badWords) {
                if (str.equals(inner)) {
                    int len = inner.length();
                    outString.append(" ");
                    outString.append("*".repeat(Math.max(0, len)));
                    outString.append(" ");
                } else {
                    outString.append(" ").append(str).append(" ");
                }
            }
        }
        return String.valueOf(outString).trim().replaceAll("\\s+", " ");
    }

}
