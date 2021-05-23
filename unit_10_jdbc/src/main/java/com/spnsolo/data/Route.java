package com.spnsolo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    Integer id;
    Integer cost;
    Integer from_id;
    Integer to_id;
}
