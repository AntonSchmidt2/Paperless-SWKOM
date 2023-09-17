package at.fhtw.swen3.SandboxSwkom.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;

import java.time.LocalDateTime;

public record Document(
        Integer ID,
        @NotBlank
        String title,
        String description,
        @NotNull
        Type type,
        LocalDateTime contentCreated,
        LocalDateTime contentUpdated
) { }
