import {
  InputLabel,
  TextField,
  TextFieldProps,
  Typography,
} from '@mui/material';
import { FC } from 'react';

export const Input: FC<TextFieldProps & { label?: string }> = ({
  label,
  sx,
  ...rest
}) => {
  return (
    <InputLabel sx={{ mt: 2, ...sx }}>
      {label && <Typography variant="h5">{label}</Typography>}
      <TextField fullWidth {...rest} />
    </InputLabel>
  );
};
