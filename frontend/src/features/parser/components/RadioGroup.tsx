import {
  InputLabel,
  RadioGroup as RadioGroupMUI,
  RadioGroupProps,
  Typography,
} from '@mui/material';
import { FC } from 'react';

export const RadioGroup: FC<RadioGroupProps & { label?: string }> = ({
  label,
  sx,
  children,
  ...rest
}) => {
  return (
    <InputLabel sx={{ mt: 2, ...sx }}>
      {label && <Typography variant="h5">{label}</Typography>}
      <RadioGroupMUI {...rest}>{children}</RadioGroupMUI>
    </InputLabel>
  );
};
