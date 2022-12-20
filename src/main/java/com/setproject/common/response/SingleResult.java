package com.setproject.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SingleResult<T> extends CommonResult {
	private T content;
}
